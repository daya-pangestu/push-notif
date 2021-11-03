
[![CircleCI](https://circleci.com/gh/circleci/circleci-docs.svg?style=svg&circle-token=1968f1d0945ea1079dba54e88a2bd3037d31985e)](https://circleci.com/gh/daya-pangestu/tugas-akhir)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=alert_status)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=bugs)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=coverage)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=vulnerabilities)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=ncloc)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Duplicated Lines (%)](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=duplicated_lines_density)](https://sonarcloud.io/dashboard?id=com.daya.taha)
[![Reliability Rating](https://sonarcloud.io/api/project_badges/measure?project=com.daya.taha&metric=reliability_rating)](https://sonarcloud.io/dashboard?id=com.daya.taha)

A send-receive notification app 

## Review tools for development

few tools used during development to ensure quality of code 

|tools|desc|review-link|file configuration
|----|----|----|----|
| [Circle CI](https://circleci.com) | continuous integration | [pipeline](https://circleci.com/gh/daya-pangestu/tugas-akhir)  | [config.yml](.circleci/config.yml) |
| [Jacoco](https://www.eclemma.org/jacoco/) | java code coverage | - | [jacoco setup](jacoco.gradle) |
| [SonarCloud.io](https://sonarcloud.io) |online-based code coverage reporting tool |[project-review](https://sonarcloud.io/organizations/taha-tugas-akhir-haha/projects) | [sonarqube config](build.gradle) |
| [BrowserStack](https://browserstack.com) |ui testing cloud-based | non-public| [browserstack config](app/browserstack_config.json) |


## Tech-Stack

this project also use many android tech stack, such as:

    - paging3
    - kotlin coroutine and flow
    - hilt
    - navigation architecture
    - flexbox
    - google dialog login


## Result

[![send-receive notification](https://img.youtube.com/vi/AyZ9INbBdYo/0.jpg)](https://youtu.be/AyZ9INbBdYo)

one of the main feature, including but not limited to:

    - paging list, load small cunk of data one at a time
    - send notification with or without image
    - track progress of sending notification
    - show/collapse notification and stack the notification 

## How This Work

in nutshell, i need to made a custom trigger in Firebase-database, whenever new data inserted, send push notification to designed topic, full implementation can be found in [this repo](https://github.com/daya-pangestu/backend-push-notif)

## License

Licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
