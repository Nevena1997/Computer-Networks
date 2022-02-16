import os
import sys
import random
import string
import socket
from randomtimestamp import randomtimestamp

prefix = "/home/ispit/Desktop/"

paths = []
for d, _, fs in os.walk('tests'):
    for f in fs:
        paths.append(f)
        for i in range(5):
            paths.append(os.path.join(d, f) + str(random.randint(10, 100)))

ips = []
for i in range(1000):
    if bool(random.getrandbits(1)):
        randip = socket.inet_ntop(socket.AF_INET, os.urandom(4))
    else:
        randip = socket.inet_ntop(socket.AF_INET6, os.urandom(16))
    ips.append(randip)

protocols = ["ftp://", "http://", "https://", "file://", "sftp://", "FILE://"]

def rand_str(n):
    return ''.join(random.choice(string.ascii_letters) for _ in range(n))

lines = []
for i in range(1000):
    time = randomtimestamp(text=False).strftime("%d/%m/%Y %H:%M:%S")
    ip = random.choice(ips)
    path = random.choice(paths)
    if not path.startswith('/'):
        path = '/' + path
    prot = random.choice(protocols)
    port = f":{random.randint(1, 65535)}" if bool(random.getrandbits(1)) else ""
    if bool(random.getrandbits(1)): 
        query = "?"
        for _ in range(random.randint(1, 3)):
            query += f"{rand_str(random.randint(1, 5))}={rand_str(random.randint(1, 5))}&"
    else:
        query = ""
    if prot.lower().startswith('file'):
        url = f"{prot}{prefix + path}"
    elif prot == "ftp://":
        url = f"{prot}user@localhost{port}{path}{query}"
    else:
        url = f"{prot}localhost{path}{port}{query}"
    lines.append("[{0}][{1}][{2}]".format(time, ip, url))

for line in lines:
    print(line)


