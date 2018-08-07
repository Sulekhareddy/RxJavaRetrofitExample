curl \
-F "status=2" \
-F "notify=0" \
-F "ipa=RxJavaRetrofitExample/build/outputs/apk/RxJavaRetrofitExample-dev-debug.apk" \
-H "X-HockeyAppToken: 70608fad3fe54663af178733a047562c" \
https://rink.hockeyapp.net/api/2/apps/RxJavaRetrofitExample/app_versions/upload