# mpi_word_count.py
from mpi4py import MPI
import re
from collections import Counter
import time

def distribute_data(comm, filename):
    # Read file and distribute data among processes
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        with open(filename, 'r') as file:
            lines = file.readlines()
    else:
        lines = None

    lines = comm.bcast(lines, root=0)

    return lines

def count_words(lines):
    # Count words in the provided lines
    words = re.findall(r'\b\w+\b', ' '.join(lines).lower())
    return Counter(words)

def main():
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        # Read input from user
        filename = input("Enter the file name: ")
    else:
        filename = None

    # Broadcast input values to all processes
    filename = comm.bcast(filename, root=0)

    start_time = time.time()

    # Distribute data among processes
    lines = distribute_data(comm, filename)

    # Count words locally
    local_word_count = count_words(lines)

    # Gather results from all processes
    word_count = comm.gather(local_word_count, root=0)

    if rank == 0:
        # Combine word counts from all processes
        total_word_count = sum(word_count, Counter())

        # Sort word counts in descending order
        sorted_word_count = dict(sorted(total_word_count.items(), key=lambda item: item[1], reverse=True))

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
