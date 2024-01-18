from numba import cuda, float32
import numpy as np
import time
import os



os.environ['NUMBA_ENABLE_CUDASIM'] = '1'  # This may help in some cases
os.environ['NUMBA_CUDA_FORCE_DEVICE_ALLOC'] = '1'  # Force allocation on the GPU
os.environ['CUDA_VISIBLE_DEVICES'] = '0'  # Set the GPU device index (change if you have multiple GPUs)



@cuda.jit
def matrix_multiply_kernel(A, B, C):
    i, j = cuda.grid(2)
    if i < C.shape[0] and j < C.shape[1]:
        temp = 0.0
        for k in range(A.shape[1]):
            temp += A[i, k] * B[k, j]
        C[i, j] = temp

def matrix_multiply_gpu(A, B):
    M, N = A.shape
    N, P = B.shape
    C = np.zeros((M, P), dtype=np.float32)

    threadsperblock = (16, 16)
    blockspergrid_x = (M + threadsperblock[0] - 1) // threadsperblock[0]
    blockspergrid_y = (P + threadsperblock[1] - 1) // threadsperblock[1]
    blockspergrid = (blockspergrid_x, blockspergrid_y)

    matrix_multiply_kernel[blockspergrid, threadsperblock](A, B, C)

    return C

def main():
    start_time = time.time()

    # Read input from user
    K = int(input("Enter the number of matrices (K): "))
    M = int(input("Enter the number of rows in matrices (M): "))
    N = int(input("Enter the number of columns in A and rows in B (N): "))
    P = int(input("Enter the number of columns in matrices (P): "))

    # Generate random matrices
    A = np.random.rand(M, N).astype(np.float32)
    B = np.random.rand(N, P).astype(np.float32)

    # Perform matrix multiplication on GPU
    C = matrix_multiply_gpu(A, B)

    end_time = time.time()
    total_time = end_time - start_time

    print("Total time taken for matrix multiplication on GPU:", total_time)

if __name__ == "__main__":
    main()
