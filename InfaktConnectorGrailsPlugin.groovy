import grails.converters.JSON
import grails.plugins.rest.client.RestBuilder

class InfaktConnectorGrailsPlugin {
    // the plugin version
    def version = "0.3-SNAPSHOT"
    // the version or versions of Grails the plugin is designed for
    def grailsVersion = "2.2 > *"
    // resources that are excluded from plugin packaging
    def pluginExcludes = [
            "grails-app/views/error.gsp"
    ]

    def title = "Infakt Connector Plugin" // Headline display name of the plugin
    def author = "Jakub Dzon"
    def authorEmail = "jakub@dzon.pl"
    def description = '''\
Grails plugin providing easy way of integrating with Infakt (http://infakt.pl) API v3.
'''

    // URL to the plugin's documentation
    def documentation = "http://grails.org/plugin/infakt-connector"

    // License: one of 'APACHE', 'GPL2', 'GPL3'
    def license = "APACHE"

    // Any additional developers beyond the author specified above.
    def developers = [[name: "Jakub Dzon", email: "jakub@dzon.pl"]]

    // Location of the plugin's issue tracker.
    def issueManagement = [system: "github", url: "https://github.com/jakub-dzon/infakt-connector/issues"]

    // Online location of the plugin's browseable source code.
    def scm = [url: "https://github.com/jakub-dzon/infakt-connector"]

    def doWithSpring = {
        restBuilder(RestBuilder)
    }

    def doWithApplicationContext = { applicationContext ->
        JSON.registerObjectMarshaller(Enum) { Enum someEnum ->
            someEnum.toString()
        }

    }
}
