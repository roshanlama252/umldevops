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

        stage('Run pipeline against a gradle project') {
            steps {
                git 'https://github.com/roshanlama252/umldevops.git'
                    stage('Build a gradle project') {
                    sh '''
                    cd Chapter08/sample1
                    chmod +x gradlew
                    ./gradlew test
                    '''
                }
            }
        }
        stage("Code coverage") {
            when {
                expression { GIT_BRANCH == "origin/main"}
                }
                steps {
                    echo "I am a master branch"
                    sh '''
                    pwd
                    cd Chapter08/sample1
                    ./gradlew jacocoTestCoverageVerification
                    ./gradlew jacocoTestReport
                    '''
                    publishHTML (target: [
                        reportDir: 'Chapter08/sample1/build/reports/jacoco/test/html',
                        reportFiles: 'index.html',
                        reportName: "JaCoCo Report"
                    ])
                }
        }
        stage("jacoco checkstyle test") {
            when {
                expression { GIT_BRANCH == "origin/feature"}
                steps {
                    echo "I am a feature branch"
                    sh '''
                    pwd
                    cd Chapter08/sample1
                    ./gradlew checkstyleMain
                    '''
                    publishHTML (target: [
                        reportDir: 'Chapter08/sample1/build/reports/checkstyle',
                        reportFiles: 'main.html',
                        reportName: "jacoco checkstyle"
                    ])
                }
            } 
         }
    }
}
