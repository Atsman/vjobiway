const HtmlWebpackPlugin = require('html-webpack-plugin');

module.exports = {
  entry: ['./src/app.jsx'],
  output: {
    path: '../../resources/public',
    filename: 'app.bundle.js',
  },

  module: {
    loaders: [
      {
        test: /\.(png|jpg|jpeg|gif|svg|woff|woff2|ttf)$/,
        loader: 'url-loader?limit=8192',
      },
      {
        test: /\.(js|jsx)$/,
        exclude: /(node_module)/,
        loaders: ['babel-loader', 'eslint-loader'],
      },
      {
        test: /\.scss$/,
        loaders: [
          'style', 
          'css?modules&importLoaders=1&localIdentName=[path]___[name]__[local]___[hash:base64:5]', 
          'postcss',
          'sass',
        ],
      },
    ],
  },

  postcss: function () {
    return [
      require('autoprefixer')
    ];
  },

  plugins: [new HtmlWebpackPlugin()],
};
