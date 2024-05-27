import re
import subprocess


def get_network_and_mask(output):
    network_regex = r'inet (\d+\.\d+\.\d+)\.\d+'
    mask_regex = r'netmask (\d+\.\d+\.\d+)\.\d+'

    network_match = re.search(network_regex, output)
    mask_match = re.search(mask_regex, output)

    if network_match and mask_match:
        network = network_match.group(1) + ".0"
        mask = mask_match.group(1)
        return f"{network}/{mask.count('255') * 8}"
    else:
        return None


def run_nmap(network_mask):
    try:
        output = subprocess.check_output(['nmap', '-sn', network_mask]).decode('utf-8')
        ip_addresses = re.findall(r'\b(?:\d{1,3}\.){3}\d{1,3}\b', output)
        return ip_addresses
    except FileNotFoundError:
        print("nmap command not found. Please make sure nmap is installed.")
        return []


def network_scan():
    try:
        output = subprocess.check_output(['ifconfig']).decode('utf-8')
        lines = output.split('\n')

        for line in lines:
            if line.strip().startswith('inet 192.168'):
                network_mask = get_network_and_mask(line)
                if network_mask:
                    print(network_mask) #to można potem wywalić
                    ip_addresses = run_nmap(network_mask)
                    print("IP addresses found in the network:") #to można potem wywalić
                    print(ip_addresses) #to można potem wywalić
                    return(ip_addresses)
    except subprocess.CalledProcessError:
        print("Error executing ifconfig.")
