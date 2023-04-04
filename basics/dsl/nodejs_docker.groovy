job('NodeJS Docker example') {
    scm {
        git('https://github.com/Michabitton/docker-cicd.git','master') {  node -> // is hudson.plugins.git.GitSCM
            node / gitConfigName('DSL User')
            node / gitConfigEmail('jenkins-dsl@devophift.work')
        }
    }
    triggers {
        scm('H/5 * * * *')
    }
   
    
    steps {
        dockerBuildAndPublish {
            repositoryName('michabi/jenkins-lab')
            tag('nodejs-dsl-docker')
            registryCredentials('dockerhub')
            buildContext('./basics/')
            forcePull(false)
            forceTag(false)
            createFingerprints(false)
            skipDecorate()
        }
    }
}

