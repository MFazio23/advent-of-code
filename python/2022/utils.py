import os


def load_lines_from_file(filename, folder='inputs', should_trim=False):
    file_path = os.path.join(folder, filename)
    file = open(file_path, 'r')
    output = file.readlines()
    if should_trim:
        output = [line.strip() for line in output]
    return output


def text_input_to_lines(text_input):
    return text_input.splitlines()