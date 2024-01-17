# cuda_pattern_count.cu
from numba import cuda
import numpy as np
import time

@cuda.jit
def count_pattern_kernel(paragraph, pattern, results):
    i = cuda.grid(1)
    if i < len(paragraph):
        if paragraph[i:i+len(pattern)] == pattern:
            cuda.atomic.add(results, 0, 1)

def main():
    # Read input from user
    filename = input("Enter the file name: ")
    pattern = input("Enter the pattern to search: ")

    # Read paragraph
    with open(filename, 'r') as file:
        paragraph = file.read()

    # Convert paragraph and pattern to numpy arrays
    paragraph_array = np.array(list(paragraph))
    pattern_array = np.array(list(pattern))

    # Allocate device memory
    d_paragraph = cuda.to_device(paragraph_array)
    d_pattern = cuda.to_device(pattern_array)
    d_results = cuda.device_array(1, dtype=int)

    threads_per_block = 128
    blocks_per_grid = (len(paragraph) + threads_per_block - 1) // threads_per_block

    start_time = time.time()

    # Launch CUDA kernel
    count_pattern_kernel[blocks_per_grid, threads_per_block](d_paragraph, d_pattern, d_results)

    # Copy results from device to host
    results = d_results.copy_to_host()[0]

    end_time = time.time()
    total_time = end_time - start_time

    print("Total time taken:", total_time)
    print("Total occurrences of the pattern:", results)

if __name__ == "__main__":
    main()
