import csv
from PIL import Image
import sys
import xml.etree.cElementTree as ET


WALKABLE = ['2', '4', '6', '8', '45', '58', '60', '73']
SLIPPERY = ['3']


def convert(input_file, output_file):
    tree = ET.parse(input_file)
    root = tree.getroot()
    layer_info = root.find('layer').attrib
    img_size = (int(layer_info['width']), int(layer_info['height']))
    data = root.find('layer/data').text.splitlines()[1:]
    reader = list(csv.reader(data, delimiter=','))
    lines = []
    for row in reader[:-1]:
        lines.append(row[:-1])
    lines.append(reader[-1])
    pixels = []
    for row in lines:
        for x in row:
            if x in WALKABLE:
                pixels.append((255, 255, 255))
            elif x in SLIPPERY:
                pixels.append((0, 255, 0))
            else:
                pixels.append((0, 0, 0))
    image = Image.new('RGB', img_size)
    image.putdata(pixels)
    image.save(output_file)

if __name__ == '__main__':
    args = sys.argv
    convert(args[1], args[2])
