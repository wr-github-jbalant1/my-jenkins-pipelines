import org.kohsuke.github.*
import com.cloudbees.hudson.plugins.folder.*
import jenkins.model.Jenkins


def genViewFolder(jobPath)
{

    def folders = jobPath.split('/');
    Jenkins jenkins = Jenkins.instance // saves some typing
    def folder = jenkins.getItem(folders[0])


    folders.each { folderName ->
        println("Folder: "+folderName)
        
        if (folder == null) 
        {
            // Create the folder if it doesn't exist or if no existing job has the same name
            println("Exist : NO  (trying to create '"+folderName+"')")
            folder = folder.createProject(Folder.class, folderName)
        }
        else
        {
            println("Exist : YES (skipping creation '"+folderName+"')")
            //folder = folder.getItem(folderName)
        }
        folder = folder.getItem(folderName)
    }

}

def call() {
   
    List dslScripts = []
    def myViewPath = ''

    node('master') {

        stage('Scan') {
            println("Search repos for DSL scripts ...")

        }

        stage('Create') {
            println("Create jobs from DSL scripts ...")
            myViewPath = 'SALAMA/pikantna'
            println("Creating view folder: "+myViewPath)
            genViewFolder(myViewPath)
            myViewPath = 'HRENOVKA/piscancja/pikantna'
            println("Creating view folder: "+myViewPath)
            genViewFolder(myViewPath)
            

        }
    }
}
