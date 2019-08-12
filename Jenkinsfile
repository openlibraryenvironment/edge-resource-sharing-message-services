#!groovy

node {

  stage('checkout') {
    checkout scm
  }

  stage ('test') {
    sh './gradlew --no-daemon --console=plain clean test'
  }

  stage ('build') {
    sh './gradlew --no-daemon --console=plain assemble'
  }

  stage ('archive') {
    sh 'ls build/libs'
    archiveArtifacts artifacts: 'build/libs/ki-rsms*'
  }

  stage ('Package') {
    if ( env.BRANCH_NAME == null ) {
      // Assume single branch should be packaged.
      echo "Single branch pipeline is assumed to require packaging."
    }
    if ( env.BRANCH_NAME == null || ['master', 'test'].contains(env.BRANCH_NAME) ) {
      def jar_files = findFiles glob: 'build/libs/*.jar'
      echo "${war_files}"
      if ( jar_files.length == 1 ) {
        echo "Single file found. ${jar_files[0].name} at ${jar_files[0].path}"
        openshift.withCluster {
          openshift.withProject {
            echo "call packager.startBuild"
          }
        }
      }
    }
  }

}
