# cuda_word_count.cu
from numba import cuda
from collections import Counter
import time

@cuda.jit
def word_count_kernel(text, word_count):
    word = cuda.shared.array(32, dtype=cuda.int8)
    idx = cuda.grid(1)

    for i in range(32):
        word[i] = 0

    start = -1
    end = -1

    if idx < len(text):
        c = text[idx]
        if c.isalpha() or c.isdigit():
            if start == -1:
                start = idx
            end = idx
        else:
            if start != -1 and end != -1:
                for i in range(end - start + 1):
                    word[i] = text[start + i]
                word[end - start + 1] = '\0'
                cuda.atomic.add(word_count, cuda.string.to_device(word), 1)

def main():
    # Read input from user
    filename = input("Enter the file name: ")

    with open(filename, 'r') as file:
        text = file.read()

    # Set up CUDA kernel and allocate device memory
    word_count = Counter()
    d_text = cuda.to_device(text.encode('utf-8'))
    d_word_count = cuda.to_device(list(word_count.keys()))

    threads_per_block = 128
    blocks_per_grid = (len(text) + threads_per_block - 1) // threads_per_block

    start_time = time.time()

    # Launch CUDA kernel
    word_count_kernel[blocks_per_grid, threads_per_block](d_text, d_word_count)

    # Copy results from device to host
    d_word_count.copy_to_host(word_count)

    # Sort word counts in descending order
    sorted_word_count = dict(sorted(word_count.items(), key=lambda item: item[1], reverse=True))

    # Display the top 10 occurrences
    top_10 = list(sorted_word_count.items())[:10]
    end_time = time.time()
    total_time = end_time - start_time

    print("Total time taken:", total_time)
    print("Top 10 occurrences:")
    for word, count in top_10:
        print(f"{word}: {count}")

if __name__ == "__main__":
    main()
