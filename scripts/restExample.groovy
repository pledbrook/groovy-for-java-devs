@Grab(group='com.github.groovy-wslite', module='groovy-wslite', version='0.8.0')
import wslite.rest.*

def location = args.size() > 0 ? args[0] : "San Francisco"

println "Getting data..."
def client = new RESTClient("http://maps.googleapis.com/maps/api/geocode/json")
def response = client.get(query: [address: location, sensor: "false"])

println response.json
