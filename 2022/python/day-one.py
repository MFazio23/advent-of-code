from itertools import groupby

from utils import text_input_to_lines, load_lines_from_file


def get_calorie_groupings(data):
    groupings = [list(group) for key, group in groupby(data, lambda x: x == '') if not key]

    return [list(int(item) for item in group) for group in groupings]


def get_highest_calorie_count(data):
    groupings = get_calorie_groupings(data)

    totals = [sum(group) for group in groupings]

    return max(totals)


def get_highest_calorie_totals(data, count):
    groupings = get_calorie_groupings(data)

    totals = [sum(group) for group in groupings]

    return sum(sorted(totals, reverse=True)[:count])


sample = """
1000
2000
3000

4000

5000
6000

7000
8000
9000

10000
""".strip()

sample_input = text_input_to_lines(sample)

result = get_highest_calorie_count(sample_input)

print(f'Sample part one = {result}')

real_input = load_lines_from_file('day-one.txt', should_trim=True)

result = get_highest_calorie_count(real_input)

print(f'Part one = {result}')

result = get_highest_calorie_totals(sample_input, 3)

print(f'Sample part two = {result}')

result = get_highest_calorie_totals(real_input, 3)

print(f'Part two = {result}')
