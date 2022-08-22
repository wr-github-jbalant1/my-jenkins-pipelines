import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins


def genViewFolder(jobPath)
{

    def folders = jobPath.split('/');
    Jenkins jenkins = Jenkins.instance // saves some typing
    def folder = jenkins.getItem(folders[0])
    //folder = jenkins.createProject(Folder.class, folders[0])
    //folders = folders[1..-1]

    folders.each { folderName ->
        if (folder.getItem(folderName) == null) 
        {
            // Create the folder if it doesn't exist or if no existing job has the same name
            folder = folder.createProject(Folder.class, folderName)
        }
        else
        {
            folder = folder.getItem(folderName)
        }
    }

}

def call() {
   
    List dslScripts = []
    //String folderName = "KLOBASA/pikantna/mehka"
    def thisJobPath = 'SALAMA/pikantna'
    def thisJobPath = 'HRENOVKA/piscancja/pikantna'

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            genViewFolder(thisJobPath)
            

        }
    }
}
