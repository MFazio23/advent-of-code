import re
from operator import countOf

from utils import load_lines_from_file

lines = load_lines_from_file('day-one.txt', should_trim=True)

first = []
second = []

for line in lines:
    a, b = re.split(r'\s+', line)

    first.append(int(a))
    second.append(int(b))

combos = list(zip(sorted(first), sorted(second)))

total = sum([abs(a - b) for a, b in combos])

print(f'Part one = {total}')

counts = []

for n in first:
    count = countOf(second, n)
    counts.append(n * count)

print(f'Part two = {sum(counts)}')