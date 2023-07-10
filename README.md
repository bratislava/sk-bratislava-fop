# SK Bratislava FOP

## Deployment
We have multiple options for deploying your app to our Kubernetes cluster.

### Pipelines

There is automatic pipeline, that is capable of building and deploying the image to our kubernetes cluster.  
It is triggered when new changes arrive to `main/master` branches or when a new tag `dev*/staging*/prod*` is pushed to repository. For example: 

```bash
git tag -a dev-latest -m "Some DEV change to the API"
git push origin dev-latest
```

or through [`bratiska-cli tag`](https://github.com/bratislava/bratiska-cli#command-tag) option.

```bash
bratiska-cli tag dev
```

### Manual
You can use our [`bratiska-cli`](https://github.com/bratislava/bratiska-cli), which takes care of deploying the app.

1. First, install the utility:

    ```bash
    yarn global add bratislava/bratiska-cli
    ```

2. then go to folder of `/strapi` or `/next` and run just this command:

    ```bash
    bartiska-cli deploy
    ```

That`s all, everything should run automatically and if not you will be notified. You can also check [user guide of bratiska-cli](https://github.com/bratislava/bratiska-cli/blob/master/README.md).
