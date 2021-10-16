pipeline {
  agent {
    kubernetes {
      yaml '''
      spec:
        containers:
        - name: gradle
          image: gradle:6.3-jdk14 
        '''
      } 
  }

    stages {
        stage('debug') {
            steps {
                echo env.GIT_BRANCH
                echo env.GIT_LOCAL_BRANCH
                }
         }
         stage('code coverage') {
            when {
                expression { 
                    return env.GIT_BRANCH == "master"
                }
            }
            steps {
                echo "I am a master branch"
                sh '''
                pwd
                cd Chapter08/sample1
                ./gradlew jacocoTestCoverageVerification
                ./gradlew jacocoTestReport
                '''
                } 
        }
        stage('jacoco checkstyle test') {
            when {
                expression { 
                    return env.GIT_BRANCH == "feature"
                }
            }
            steps {
                echo "I am a feature branch"
                sh '''
                pwd
                cd Chapter08/sample1
                ./gradlew checkstyleMain
                '''
            }
        }
    }
}
