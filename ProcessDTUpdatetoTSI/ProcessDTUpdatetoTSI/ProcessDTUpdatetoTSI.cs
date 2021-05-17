using System.Collections.Generic;
using System.Threading.Tasks;
using System.Text;
using Microsoft.Azure.EventHubs;
using Microsoft.Azure.WebJobs;
using Microsoft.Extensions.Logging;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace UpdateTSI
{
    public static class ProcessDTUpdatetoTSI
    {
        [FunctionName("ProcessDTUpdatetoTSI")]
        public static async Task Run(
            [EventHubTrigger("TwinHub116117", Connection = "Endpoint=sb://namespace116117.servicebus.windows.net/;SharedAccessKeyName=AuthRule116117;SharedAccessKey=xKel4e7bP5oaKnDDt+RLyn6WG8YItGFPwa6IHUelrBo=;EntityPath=TwinHub116117")] EventData myEventHubMessage,
            [EventHub("TimeSeriesHub116117", Connection = "Endpoint=sb://namespace116117.servicebus.windows.net/;SharedAccessKeyName=AuthRuleTSH116117;SharedAccessKey=mNgx714FUuoYlxdL9IWfF7GN97okADEtWmCj0VgqGWo=;EntityPath=TimeSeriesHub116117")] IAsyncCollector<string> outputEvents,
            ILogger log)
        {
            JObject message = (JObject)JsonConvert.DeserializeObject(Encoding.UTF8.GetString(myEventHubMessage.Body));
            log.LogInformation($"Reading event: {message}");

            // Read values that are replaced or added
            var tsiUpdate = new Dictionary<string, object>();
            foreach (var operation in message["patch"])
            {
                if (operation["op"].ToString() == "replace" || operation["op"].ToString() == "add")
                {
                    //Convert from JSON patch path to a flattened property for TSI
                    //Example input: /Front/Temperature
                    //        output: Front.Temperature
                    string path = operation["path"].ToString().Substring(1);
                    path = path.Replace("/", ".");
                    tsiUpdate.Add(path, operation["value"]);
                }
            }
            // Send an update if updates exist
            if (tsiUpdate.Count > 0)
            {
                tsiUpdate.Add("$dtId", myEventHubMessage.Properties["cloudEvents:subject"]);
                await outputEvents.AddAsync(JsonConvert.SerializeObject(tsiUpdate));
            }
        }
    }
}