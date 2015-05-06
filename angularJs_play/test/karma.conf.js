module.exports = function(config){
  config.set({

    basePath : '../',

    files : [
      'public/js/angular.js',
      'public/js/angular-route.js',
      'public/js/jquery.min.js',
      'public/js/bootstrap.min.js',
      'bower_components/angular-mocks/angular-mocks.js',
      'public/js/**/*.js',
      'test/unit/**/*.js'
    ],

    autoWatch : true,

    frameworks: ['jasmine'],

    browsers : ['Chrome'],

    plugins : [
            'karma-chrome-launcher',
            'karma-firefox-launcher',
            'karma-jasmine'
            ],

    junitReporter : {
      outputFile: 'test_out/unit.xml',
      suite: 'unit'
    }

  });
};