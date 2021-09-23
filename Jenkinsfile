podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: 'maven' 
        image: 'maven:3.8.1-jdk-8'
    ''') {

    node(POD_LABEL) {
        stage('Build') {
            git 'https://github.com/dlambrig/simple-java-maven-app.git'
            container('maven') {
                stage('Build a Maven project') {
                    sh '''
                    echo "maven build"
                    mvn -B -DskipTests clean package
                    ''' 
            }
        }
    }
}
