firewall
=========
Iptables is a command-line tool that allows you to configure Netfilter,
Linux kernel module that provides firewalls, Internet network sharing and network traffic history.
Firewall is a bash program which uses the Iptables command that:
- opens ports used on most servers (HTTP-80, HTTPS-443, SSH-22, etc.)
- maintains other ports closed.

Requirements
------------

- Iptables

Role Variables
--------------

| Name	        | Default Value	| Description|
| ------------- |:-------------:| ----------:|
|firewall_network_interface|eth0|Network interface.|
|firewall_script_files|-|Bash scripts used by firewall program.|
|firewall_goss_enabled|False|Enable goss to check firewall after installation.|
|firewall_input_allowed_ports|-|Input open ports.|
|firewall_output_allowed_ports|-|Output open ports.|
|firewall_nexus_port|-|Open (input & output direction) port for Nexus repository.|
|firewall_enabled_logs_input_trafic|True|Enable log input trafic|
|firewall_enabled_logs_output_trafic|True|Enable log output trafic|

Dependencies
------------

None.

Example Playbook
----------------

Install firewall
```yaml
- hosts: all
  roles:
    - { role: firewall }
```

License
-------

BSD

Author Information
------------------

Eric LEGBA.