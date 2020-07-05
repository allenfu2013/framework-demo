package me.coding.sample.elasticjob;

// 目前提供了三种任务分片策略，分片策略的实现最终是在注册中心zk中在分片的instance中写入实例信息

// （1）AverageAllocationJobShardingStrategy：基于平均分配算法的分片策略.

//   如果分片不能整除, 则不能整除的多余分片将依次追加到序号小的服务器.
//   如: 
//   1. 如果有3台服务器, 分成9片, 则每台服务器分到的分片是: 1=[0,1,2], 2=[3,4,5], 3=[6,7,8].
//   2. 如果有3台服务器, 分成8片, 则每台服务器分到的分片是: 1=[0,1,6], 2=[2,3,7], 3=[4,5].
//   3. 如果有3台服务器, 分成10片, 则每台服务器分到的分片是: 1=[0,1,2,9], 2=[3,4,5], 3=[6,7,8].

// （2）devitySortByNameJobShardingStrategy：根据作业名的哈希值奇偶数决定IP升降序算法的分片策略.
//   首先 作业名的哈希值为奇数则IP升序. 作业名的哈希值为偶数则IP降序.然后再调用AverageAllocationJobShardingStrategy的平均分片算法进行分片。

// （3）RotateServerByNameJobShardingStrategy：根据作业名的哈希值对服务器列表进行轮转的分片策略.