pipeline {
    // 声明 Jenkins 使用 Kubernetes 动态 Pod
    agent {
        kubernetes {
            yaml """
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: maven
    image: maven:3.9.9-eclipse-temurin-17
    command:
    - cat
    tty: true

  - name: kaniko
    image: gcr.io/kaniko-project/executor:debug
    command:
    - cat
    tty: true
    volumeMounts:
    - name: docker-config
      mountPath: /kaniko/.docker/

  volumes:
  - name: docker-config
    secret:
      secretName: dockerhub-secret
"""
        }
    }

    environment {
        // Docker Hub 镜像地址
        IMAGE_NAME = "diebansi/demo-maven:latest"
    }

    stages {
        stage('Checkout SCM') {
            steps {
                // 拉取 Git 仓库
                checkout scm
            }
        }

        stage('Build JAR') {
            steps {
                container('maven') {
                    // Maven 打包
                    sh 'mvn clean package -DskipTests'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                container('kaniko') {
                    // 使用 Kaniko 构建镜像
                    sh """
                    /kaniko/executor \
                        --context=\$WORKSPACE \
                        --dockerfile=Dockerfile \
                        --destination=\$IMAGE_NAME \
                        --insecure
                    sleep 300    
                    """
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline finished successfully. Image: ${env.IMAGE_NAME}"
        }
        failure {
            echo "Pipeline failed."
        }
    }
}
