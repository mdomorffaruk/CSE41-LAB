# cuda_matrix_multiply.cu
import numpy as np
from numba import cuda
import time

@cuda.jit
def matrix_multiply(A, B, C):
    row, col = cuda.grid(2)
    if row < C.shape[0] and col < C.shape[1]:
        tmp = 0.0
        for k in range(A.shape[1]):
            tmp += A[row, k] * B[k, col]
        C[row, col] = tmp

def main():
    # Read input from user
    K = int(input("Enter the number of matrices (K): "))
    M = int(input("Enter the number of rows in matrices (M): "))
    N = int(input("Enter the number of columns in A and rows in B (N): "))
    P = int(input("Enter the number of columns in matrices (P): "))

    # Generate random matrices A and B
    A = np.random.rand(K, M, N)
    B = np.random.rand(K, N, P)

    # Allocate memory for the result matrices C
    C = np.zeros((K, M, P))

    # Configure the CUDA kernel
    threadsperblock = (16, 16)
    blockspergrid_x = (M + threadsperblock[0] - 1) // threadsperblock[0]
    blockspergrid_y = (P + threadsperblock[1] - 1) // threadsperblock[1]
    blockspergrid = (blockspergrid_x, blockspergrid_y)

    start_time = time.time()

    # Perform matrix multiplication on GPU
    for k in range(K):
        matrix_multiply[blockspergrid, threadsperblock](A[k], B[k], C[k])

    end_time = time.time()
    total_time = end_time - start_time

    print("Total time taken for matrix multiplication:", total_time)

if __name__ == "__main__":
    main()
