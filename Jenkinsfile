podTemplate(containers: [
    containerTemplate(
        name: 'gradle',
        image: 'gradle:6.3-jdk14',
        command: 'sleep',
        args: '30d'
        ),
])  {

    node(POD_LABEL) {
        stage('Run pipeline against a gradle project') {
            git 'https://github.com/roshanlama252/umldevops.git'
            container('gradle') {
                stage('Build a gradle project') {
                sh '''
                cd Chapter08/sample1
                chmod +x gradlew
                ./gradlew test
                '''
                }
        stage("Code coverage") {
            if (env.GIT_BRANCH == "origin/master") {
            steps {
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
        }
        stage("jacoco checkstyle test") {
            if (env.GIT_BRANCH == "origin/feature") {
            steps {
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
  }
}
