#You need to have downloaded the Fuseki html pages before and put
#them into a 'fuseki-pages' folder
lein run -m org.apache.jena.fuseki.FusekiCmd --pages=fuseki-pages --update --mem ~/here
