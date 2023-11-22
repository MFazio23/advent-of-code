from utils import load_lines_from_file, text_input_to_lines

# print(load_lines_from_file('day-one.txt', should_trim=True))

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
print(text_input_to_lines(sample))

