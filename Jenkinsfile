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
            when {
                expression { 
                    return env.GIT_BRANCH == "origin/main"
                }
            }
            steps {
                echo "I am a master branch"
                } 
        }
        stage('jacoco checkstyle test') {
            when {
                expression { 
                    return env.GIT_BRANCH == "origin/feature"
                }
            }
            steps {
                echo "I am a feature branch"
            }
        }
    }
}
