**Trending Hashtags**
 
**To Run Application**
    prerequisite:
        ● Java 8 or above
        ● Maven 3+
 
   To Build: 
        execute common on project directory
            mvn clean install
     
    Once build succeed
     java -jar trending-hashtags-1.0.jar
     Or if you have open in eclipse/intellij idea/STS then just click on class TwitterTrendingApplication  run as java application
	 
** Run tweets.sh/tweets.bat

 **API Details**
 
 APIs request/response is below
 
message category-lookup
http://localhost:8080/tweet
{
    "tweet": ""
}

response
{
Success
}

create message type by company
URL POST http://localhost:8080/
response 
{"hashtags" : ["<hashtag1>", "<hashtag2>", "...", "<hashtag25>"]}