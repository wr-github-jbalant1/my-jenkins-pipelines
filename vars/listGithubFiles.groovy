import org.kohsuke.github.*

def call() {
   
    List dslScripts = []

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            //folder('folder-a') { description('Folder containing all jobs for folder-a')  }
            steps{
                jobDsl scriptText: "folder('New Folder')"
            }
        }
    }
}
