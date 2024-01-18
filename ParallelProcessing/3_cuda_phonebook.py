# mpi_phonebook_search.py
from mpi4py import MPI
import time

def distribute_data(comm, filename):
    # Read phonebook and distribute data among processes
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        with open(filename, 'r') as file:
            phonebook = [line.strip().split(',') for line in file]
    else:
        phonebook = None

    phonebook = comm.bcast(phonebook, root=0)

    return phonebook

def search_contacts(phonebook, name):
    # Search for contacts matching the given name
    matching_contacts = [(contact[0], contact[1]) for contact in phonebook if name.lower() in contact[0].lower()]
    return matching_contacts

def main():
    comm = MPI.COMM_WORLD
    rank = comm.Get_rank()
    size = comm.Get_size()

    if rank == 0:
        # Read input from user
        filename = input("Enter the phonebook file name: ")
        name_to_search = input("Enter the name to search: ")
    else:
        filename, name_to_search = [None] * 2

    # Broadcast input values to all processes
    filename = comm.bcast(filename, root=0)
    name_to_search = comm.bcast(name_to_search, root=0)

    start_time = time.time()

    # Distribute data among processes
    phonebook = distribute_data(comm, filename)

    # Search for contacts locally
    local_matching_contacts = search_contacts(phonebook, name_to_search)

    # Gather results from all processes
    matching_contacts = comm.gather(local_matching_contacts, root=0)

    if rank == 0:
        # Flatten the list of matching contacts
        all_matching_contacts = [contact for sublist in matching_contacts for contact in sublist]
        end_time = time.time()
        total_time = end_time - start_time

        print("Total time taken:", total_time)
        print("Matching contacts:")
        for name, number in all_matching_contacts:
            print(f"{name}: {number}")

if __name__ == "__main__":
    main()
