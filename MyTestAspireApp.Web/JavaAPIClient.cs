namespace MyTestAspireApp.Web
{
    public class JavaAPIClient(HttpClient httpClient)
    {
        public Task<string> GetName()
        {
            return httpClient.GetStringAsync("/name");
        }
    }
}
