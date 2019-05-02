# Echo function

This is a very simple 'echo' function which logs the recieved event payload and returns the same. You can use it to test Functions integration with OCI Events service.

## Pre-requisites

Before you begin, please ensure that you have configure the Oracle Functions development environment. 

### Fn CLI

Download (or update) the [Fn CLI](https://github.com/fnproject/cli)

`curl -LSs https://raw.githubusercontent.com/fnproject/cli/master/install | sh`

### Switch to correct context

- `fn use context <your context name>`
- Check using `fn ls apps`

### Syslog service

Oracle Functions allows you to push the function logs to a syslog endpoint. Please use [the following as an example](https://github.com/abhirockzz/fn-syslog-example) of how to configure the application to use [Papertrail](https://papertrailapp.com) as the syslog endpoint.

Once you have completed the pre-requisites, clone this repository - `git clone https://github.com/abhirockzz/fn-echo-app` and change to the correct directory - `cd fn-echo-app`

## Create application

You can use the console or CLI to create the app

`fn create app fn-echo-app --annotation oracle.com/oci/subnetIds='SUBNET_OCIDs' --syslog-url <SYSLOG_ENDPOINT>`

e.g.

    fn create app fn-echo-app --annotation oracle.com/oci/subnetIds='["ocid1.subnet.oc1.phx.aaaaaaaaghmsma7mpqhqdhbgnby25u2zo4wqlrrcskvu7jg56dryxtfoobar"]' --syslog-url tcp://logz7.foobar.com:42424

## Deploy the application

`fn -v deploy --app fn-echo-app` 

### Check deployed application

`fn inspect app fn-echo-app`

## Test

The `sample-event-payload.json` file (included in the repository) represents a sample payload sent by the OCI Events service when an object is created in a OCI Storage Bucket.

You can test the function by piping the contents of that file

`cat sample-event-payload.json | fn invoke fn-echo-app echo-event`

You should see the JSON payload in the reponse and the same should be evident in the function logs (Papertrail) as well.

## Next steps

Your next logical step would be to configure OCI Events service to trigger this function based on an event e.g. object created in an OCI Object Storage bucket and then check the function logs to see the payload being sent by the service.