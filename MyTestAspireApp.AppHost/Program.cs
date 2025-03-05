using Aspire.Hosting.ApplicationModel;
using Microsoft.Extensions.Hosting;

var builder = DistributedApplication.CreateBuilder(args);

var apiService = builder.AddProject<Projects.MyTestAspireApp_ApiService>("apiservice");

var postgresDB = builder.AddPostgres("localPg")
    .AddDatabase("postgres");

string? postgresHostname = "", postgresUsername = "", postgresPassword = "", postgresDBName = "";
postgresHostname = "localhost" + ":" + postgresDB.Resource.Parent.PrimaryEndpoint.Port;
postgresDBName = postgresDB.Resource.DatabaseName;
postgresUsername = postgresDB.Resource.Parent.UserNameParameter?.Value;
postgresPassword = postgresDB.Resource.Parent.PasswordParameter?.Value;


var javaApi = builder.AddSpringApp("javaapi", "../Other Dependencies/sampleapi",
        new JavaAppExecutableResourceOptions() {
             OtelAgentPath = "lib/",
             ApplicationName = "target/sampleapi-0.0.1-SNAPSHOT.jar",
             Port=8090
        })
    .WithEnvironment("DB_URL", $"jdbc:postgresql://{postgresHostname}/{postgresDBName}")
    .WithEnvironment("DB_USERNAME", $"{postgresUsername}")
    .WithEnvironment("DB_PASSWORD", $"{postgresPassword}")
    .WithOtlpExporter()
    .WithHttpEndpoint(8090, name: "javaapi-http", isProxied:false)
    .WithReference(apiService)
    .WithReference(postgresDB);

builder.AddProject<Projects.MyTestAspireApp_Web>("webfrontend")
    .WithExternalHttpEndpoints()
    .WithReference(apiService)
    .WithReference(javaApi);

builder.Build().Run();