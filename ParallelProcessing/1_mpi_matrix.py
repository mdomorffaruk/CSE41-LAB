# mpi_matrix_multiply.py
from mpi4py import MPI
import numpy as np
import time

def generate_random_matrix(rows, cols):
    return np.random.rand(rows, cols)

def distribute_data(comm, K, M, N, P):
    # Distribute matrices A and B among processes
    rank = comm.Get_rank()
    size = comm.Get_size()

    rows_per_process = M // size

    A_local = generate_random_matrix(rows_per_process, N)
    B = generate_random_matrix(N, P)

    comm.Bcast(B, root=0)

    return A_local, B

def matrix_multiply(A_local, B_local):
    return np.dot(A_local, B_local)

def main():
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()
    size = comm.Get_size()

    start_time = time.time()

    if rank == 0:
        # Read input from user
        K = int(input("Enter the number of matrices (K): "))
        M = int(input("Enter the number of rows in matrices (M): "))
        N = int(input("Enter the number of columns in A and rows in B (N): "))
        P = int(input("Enter the number of columns in matrices (P): "))
    else:
        K, M, N, P = [None] * 4

    # Broadcast input values to all processes
    K, M, N, P = comm.bcast((K, M, N, P), root=0)

    # Distribute data among processes
    A_local, B = distribute_data(comm, K, M, N, P)

    # Perform matrix multiplication
    C_local = matrix_multiply(A_local, B)

    # Gather results from all processes
    C = comm.gather(C_local, root=0)

    if rank == 0:
        end_time = time.time()
        total_time = end_time - start_time

        print("Total time taken for matrix multiplication:", total_time)

if __name__ == "__main__":
    main()

