using Microsoft.Extensions.Hosting;

namespace Aspire.Hosting;

internal static class HostingExtensions
{
    //public static IResourceBuilder<ContainerResource> RunWithHttpsDevCertificate(this IResourceBuilder<ContainerResource> builder, string certFileEnv, string certKeyFileEnv)
    //{
    //    if (builder.ApplicationBuilder.ExecutionContext.IsRunMode && builder.ApplicationBuilder.Environment.IsDevelopment())
    //    {
    //        DevCertHostingExtensions.RunWithHttpsDevCertificate(builder, certFileEnv, certKeyFileEnv, (certFilePath, certKeyPath) =>
    //        {
    //            builder.WithHttpsEndpoint(env: "HTTPS_PORT");
    //            var httpsEndpoint = builder.GetEndpoint("https");

    //            builder.WithEnvironment(context =>
    //            {
    //                // Configure Node to trust the ASP.NET Core HTTPS development certificate as a root CA.
    //                if (context.EnvironmentVariables.TryGetValue(certFileEnv, out var certPath))
    //                {
    //                    context.EnvironmentVariables["NODE_EXTRA_CA_CERTS"] = certPath;
    //                    context.EnvironmentVariables["HTTPS_REDIRECT_PORT"] = ReferenceExpression.Create($"{httpsEndpoint.Property(EndpointProperty.Port)}");
    //                }
    //            });
    //        });
    //    }

    //    return builder;
    //}
}
