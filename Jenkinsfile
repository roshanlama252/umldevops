podTemplate(yaml: '''
    apiVersion: v1
    kind: Pod
    spec:
      containers:
      - name: 'jnlp' 
        image: 'maven:3.8.1-adoptopenjdk-11'
    ''') {

    node(POD_LABEL) {
        stage('Build') { 
            steps {
                sh 'mvn -B -DskipTests clean package' 
            }
        }
    }
}
