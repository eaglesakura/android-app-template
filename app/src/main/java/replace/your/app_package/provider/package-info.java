/**
 * Garnetを経由して依存注入するProviderオブジェクトを配置する
 *
 * 理想的には、アーキテクチャ内の異なる層の繋ぎ込みをGarnetを通じて行う。
 *
 * Activity/Fragment <- Garnet Provider ->   ViewModel
 * ViewModel         <- Garnet Provider ->   Repository
 * Repository        <- Garnet Provider ->   Data Source
 */
package replace.your.app_package.provider;
