import sys


def get_m(k):
    m = 0
    while m*(m-1)/2 <= k:
        m += 1
    else:
        m -= 1
    return m


def generate(l, k):
    """
    Generate array of length l with k inversions.
    """
    # Generate a sorted array of length l
    arr = list(range(0, l))

    # If no inversions are needed, return sorted array.
    if k == 0:
        return arr

    # Find largest m such that m*(m-1)/2 <= k
    m = get_m(k)
    print(m)
    # Reverse first m elements in the array which will give m*(m-1)/2 inversions
    arr = arr[m-1::-1]+arr[m:]

    # Calculate for any remaining inversions
    remaining_k = k-(m*(m-1)/2)
    print(remaining_k)
    # For remaining inversions, move the last element to its left by remaining_k
    if remaining_k > 0:
        print(arr)
        arr.insert(int(len(arr)-remaining_k - 1), arr[-1])
        print(arr)
        arr = arr[:-1]

    return arr


if __name__ == '__main__':
    l = int(sys.argv[1])
    k = int(sys.argv[2])
    arr = generate(l, k)
    # print(arr)
