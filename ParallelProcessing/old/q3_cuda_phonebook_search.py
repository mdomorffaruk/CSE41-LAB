# cuda_phonebook_search.cu
from numba import cuda
import numpy as np
import time

@cuda.jit
def search_kernel(phonebook, name, results):
    i = cuda.grid(1)
    if i < len(phonebook):
        if name.lower() in phonebook[i, 0].lower():
            results[i, 0] = phonebook[i, 0]
            results[i, 1] = phonebook[i, 1]

def main():
    # Read input from user
    filename = input("Enter the phonebook file name: ")
    name_to_search = input("Enter the name to search: ")

    # Read phonebook
    phonebook = np.genfromtxt(filename, dtype=str, delimiter=',', autostrip=True)

    # Allocate device memory
    d_phonebook = cuda.to_device(phonebook)
    d_results = cuda.device_array_like(phonebook[:, :2])

    threads_per_block = 128
    blocks_per_grid = (len(phonebook) + threads_per_block - 1) // threads_per_block

    start_time = time.time()

    # Launch CUDA kernel
    search_kernel[blocks_per_grid, threads_per_block](d_phonebook, name_to_search, d_results)

    # Copy results from device to host
    results = d_results.copy_to_host()

    # Filter out empty rows
    results = results[results[:, 0] != '']

    end_time = time.time()
    total_time = end_time - start_time

    print("Total time taken:", total_time)
    print("Matching contacts:")
    for name, number in results:
        print(f"{name}: {number}")

if __name__ == "__main__":
    main()
