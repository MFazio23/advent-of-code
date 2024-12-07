import argparse
import os
from kotlin_templates import part_template, part_test_template

base_path = './../../..'

number_text_dict = {
    1: "one",
    2: "two",
    3: "three",
    4: "four",
    5: "five",
    6: "six",
    7: "seven",
    8: "eight",
    9: "nine",
    10: "ten",
    11: "eleven",
    12: "twelve",
    13: "thirteen",
    14: "fourteen",
    15: "fifteen",
    16: "sixteen",
    17: "seventeen",
    18: "eighteen",
    19: "nineteen",
    20: "twenty",
    21: "twentyone",
    22: "twentytwo",
    23: "twentythree",
    24: "twentyfour",
    25: "twentyfive",
    26: "twentysix",
    27: "twentyseven",
    28: "twentyeight",
    29: "twentynine",
    30: "thirty"
}

def get_part_text(year, day):
    return {
        "PartOne": part_template.format(partFunction="partOne", year=year, day=day),
        "PartTwo": part_template.format(partFunction="partTwo", year=year, day=day),
        "PartOneTest": part_test_template.format(partName="PartOne", partFunction="partOne", partNumber="one", year=year, day=day),
        "PartTwoTest": part_test_template.format(partName="PartTwo", partFunction="partTwo", partNumber="two", year=year, day=day),
    }

print("Creating day file...")

arg_parser = argparse.ArgumentParser()
arg_parser.add_argument("-year", "-y", help="Event year", type=int)
arg_parser.add_argument("-day", "-d", help="Event day", type=int)

args = arg_parser.parse_args()

year_text = number_text_dict[args.year - 2000]
day_text = number_text_dict[args.day]

part_text = get_part_text(year_text, day_text)

# 2018/kotlin/src/main/kotlin/dayone/PartOne.kt
kotlin_path = f"{base_path}/kotlin/advent-of-code-{args.year}"
main_path = f"src/main"
test_path = f"src/test"
code_path = f"kotlin/day{day_text}"
resources_path = "resources"

for file_name, file_text in part_text.items():
    source_set_path = test_path if "Test" in file_name else main_path
    path = f'{kotlin_path}/{source_set_path}/{code_path}'

    print(f"Creating {path} directory...")
    os.makedirs(path, exist_ok=True)

    with open(f'{path}/{file_name}.kt', "w") as part_file:
        print(f"Writing {file_name}.kt to {path}...")
        part_file.write(file_text)

for source_set_path in [main_path, test_path]:
    path = f'{kotlin_path}/{source_set_path}/{resources_path}'
    print(f"Creating {path} directory...")
    os.makedirs(path, exist_ok=True)
    print(f"Creating empty day-{day_text}.txt resource...")
    with open(f"{path}/day-{day_text}.txt", "a"): pass
    print(f"Creating empty day-{day_text}-small.txt resource...")
    with open(f"{path}/day-{day_text}-small.txt", "a"): pass

print("Done!")