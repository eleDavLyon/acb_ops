#!/bin/bash
# On utilise set +x pour ne pas afficher les commandes sur la console
set +x
set -e
echo "####################################"
echo "=========== Debut Deploiement======="
echo "####################################"

echo -n "Current directory : ";pwd
echo -n "Current user : "; whoami
cd ansible


export ANSIBLE_FORCE_COLOR="true"
export ANSIBLE_HOST_KEY_CHECKING="False"
export ANSIBLE_KEEP_REMOTE_FILES=1

debug_option=""
limit=""
tags="-t always"

if [ "${debug}" == "true" ]; then
    debug_option="-vvv"
fi


if [ "${firewall}" == "true" ]; then
    tags="${tags},firewall"
fi

if [ "${openjdk8}" == "true" ]; then
     tags="${tags},openjdk8"
fi

if [ "${postgres}" == "true" ]; then
     tags="${tags},postgres"
fi

if [ "${app_acb}" == "true" ]; then
     tags="${tags},app_acb"
fi

if [ "${installComplete}" == "true" ]; then
  tags=""
fi

echo "---" > extra_vars.yml
echo "env: ${environment}" >>extra_vars.yml
echo "app_acb_version: ${app_acb_version}" >>extra_vars.yml

echo "-- Ansible version --"
ansible --version
echo "---------------------"

# Gestion des mots de passe encryptés par Ansible-vault
VAULT_PASSWORD="test"
echo $VAULT_PASSWORD > vault_pass.txt

INVENTORY_HOST_FILE=inventory/${environment}/hosts.yml
ansible-playbook --vault-password-file vault_pass.txt -i $INVENTORY_HOST_FILE playbook.yml ${debug_option} ${tags} -e "@extra_vars.yml"
rm -f extra_vars.yml vault_pass.txt

echo "=========== Fin Deploiement======="