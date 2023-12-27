def tomcatServerUrl = "http://172.31.40.78"

node {
  agent {
    node {
      label 'jenkins-slave'
    }
  }
tools{maven 'Maven'}
  
    stage('Build') {
      steps {
        // Run the maven build
        sh '"mvn" -Dmaven.test.failure.ignore clean install'
      }
    }

    stage('Deploy') {
  steps {
    script {
     deploy adapters: [tomcat9(credentialsId: 'tomcat-credentials', path: '', url: 'http://172.31.40.78:8080')], contextPath: 'jenkinsfile.groovy', war: '**/*.war'
    }
  }
}
  
}
