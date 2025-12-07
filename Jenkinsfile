pipeline {
  agent any

  triggers {
    githubPush()
  }

  stages {
    stage('Checkout') {
      steps {
        checkout scm
      }
    }

    stage('Hello') {
      steps {
        sh 'echo "Hello from Jenkins running on Kubernetes!"'
        sh 'uname -a'
      }
    }
  }
}
