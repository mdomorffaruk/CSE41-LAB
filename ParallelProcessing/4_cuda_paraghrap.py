# mpi_pattern_count.py
from mpi4py import MPI
import re
import time

def distribute_data(comm, filename):
    # Read paragraph and distribute data among processes
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        with open(filename, 'r') as file:
            paragraph = file.read()
    else:
        paragraph = None

    paragraph = comm.bcast(paragraph, root=0)

    return paragraph

def count_pattern_occurrences(paragraph, pattern):
    # Count occurrences of the given pattern in the paragraph
    occurrences = len(re.findall(pattern, paragraph))
    return occurrences

def main():
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        # Read input from user
        filename = input("Enter the file name: ")
        pattern = input("Enter the pattern to search: ")
    else:
        filename, pattern = [None] * 2

    # Broadcast input values to all processes
    filename = comm.bcast(filename, root=0)
    pattern = comm.bcast(pattern, root=0)

    start_time = time.time()

    # Distribute data among processes
    paragraph = distribute_data(comm, filename)

    # Count occurrences locally
    local_occurrences = count_pattern_occurrences(paragraph, pattern)

    # Gather results from all processes
    total_occurrences = comm.reduce(local_occurrences, op=MPI.SUM, root=0)

    if rank == 0:
        end_time = time.time()
        total_time = end_time - start_time

        print("Total time taken:", total_time)
        print("Total occurrences of the pattern:", total_occurrences)

if __name__ == "__main__":
    main()
