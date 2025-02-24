using Microsoft.Extensions.Hosting;

var builder = DistributedApplication.CreateBuilder(args);

var apiService = builder.AddProject<Projects.MyTestAspireApp_ApiService>("apiservice");

builder.AddProject<Projects.MyTestAspireApp_Web>("webfrontend")
    .WithExternalHttpEndpoints()
    .WithReference(apiService);

var javaproject = builder.AddDockerfile("javaproject", "../Other Dependencies/sampleapi")
    .WithEnvironment("DB_URL", "jdbc:postgresql://host.docker.internal/postgres")
    .WithOtlpExporter()
    .WithHttpEndpoint(8090, 8090);


var launchProfile = builder.Configuration["DOTNET_LAUNCH_PROFILE"] ??
                    builder.Configuration["AppHost:DefaultLaunchProfileName"]; // work around https://github.com/dotnet/aspire/issues/5093

if (builder.Environment.IsDevelopment() && launchProfile == "https")
{
    javaproject.RunWithHttpsDevCertificate("HTTPS_CERT_FILE", "HTTPS_CERT_KEY_FILE");
}

builder.Build().Run();