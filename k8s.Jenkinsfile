pipeline {
    agent any

    options {
        buildDiscarder(logRotator(numToKeepStr: '30', artifactNumToKeepStr: '30'))
    }

    environment {
        DOCKER_TOKEN = credentials('docker-push-secret')
        DOCKER_USER = 'giannicapocci'
        DOCKER_SERVER = 'ghcr.io'
        DOCKER_PREFIX = 'ghcr.io/giannicapocci/devops-spring'
    }

    stages {
        stage('Checkout') {
            steps {
                git branch: 'master', url: 'git@github.com:GianniCapocci/DevOps.git'
            }
        }
        stage('Docker build and push') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    docker build --rm -t $DOCKER_PREFIX:$TAG -t $DOCKER_PREFIX:latest  -f nonroot.Dockerfile .
                    echo $DOCKER_TOKEN | docker login $DOCKER_SERVER -u $DOCKER_USER --password-stdin
                    docker push $DOCKER_PREFIX --all-tags
                '''
            }
        }
        stage('Deploy to k8s') {
            steps {
                sh '''
                    HEAD_COMMIT=$(git rev-parse --short HEAD)
                    TAG=$HEAD_COMMIT-$BUILD_ID
                    # if we had multiple configurations in kubeconfig file, we should select the correct one
                    # kubectl config use-context devops
                    kubectl set image deployment/spring-deployment spring=$DOCKER_PREFIX:$TAG
                    kubectl rollout status deployment spring-deployment --watch --timeout=2m
                '''
            }
        }
    }
}