pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'git@github.com:GianniCapocci/DevOps.git'
            }
        }
        stage('run ansible pipeline') {
            steps {
                build job: 'ansible'
            }
        }
        stage('Install mysql') {
            steps {
                sh '''
                    export ANSIBLE_CONFIG=~/workspace/ansible/ansible.cfg
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-db-server ~/workspace/ansible/playbooks/mysql.yaml
                '''
            }
        }
        stage('Deploy app') {
            steps {
                sh '''
                    ansible-playbook -i ~/workspace/ansible/hosts.yaml -l azure-app-server ~/workspace/ansible/playbooks/spring.yaml
                '''
            }
        }
    }
}