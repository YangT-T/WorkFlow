# Module3 流程与业务逻辑
#设计说明
**成员：** 杨昊 张远驰 何宇轩
[toc]
**** 

## 程序描述
### 登录模块
&emsp;&emsp;登录模块由其他小组负责，在登录完成后打开与后端交互的Session，并使用唯一ID对用户进行标识。
### BPMN绘制模块
&emsp;&emsp;**功能1**：为用户提供web页面绘制业务流程图，生成BPMN文件，将结果保存至本地
&emsp;&emsp;**功能2**：为用户提供流程图模板，辅助用户基于模板进行流程定义
&emsp;&emsp;BPMN 即 Business Process Model and Notation，是一种用于描述业务流程的图形标记语言，可以用来建模和文档化数据流程，通常用于业务流程管理和工作流程建模。Activiti 引擎提供了对 BPMN 良好支持，可以对其进行储存、解析等操作。

### 流程部署模块
&emsp;&emsp;**功能1**：用户在 web 页面中选择 BPMN 文件，进行流程部署
&emsp;&emsp;流程部署是 activiti 启动的第一步，部署完的流程将被存至数据库中，保存文件内容、流程定义相关的信息。
### 启动实例模块
&emsp;&emsp;**功能1**：根据权限，向用户展示其可以访问的已部署流程
&emsp;&emsp;**功能2**：用户选择需要发起的流程，在弹窗中输入需要指定的属性，启动流程实例
### 任务操作模块
&emsp;&emsp;**功能1**：用户完成分配给其的某项任务
&emsp;&emsp;**功能2**：为用户提供链接，查看与任务相关的信息

### 历史记录模块
&emsp;&emsp;**功能1**：向用户展示正在分派给他，或曾经分派给他的任务
&emsp;&emsp;**功能2**：历史记录过滤

## 整体架构
&emsp;&emsp;流程与业务逻辑模块基于 Activiti 逻辑引擎进行构建，并使用 Spring 框架整合。Activiti 是一个开源的业务流程管理（BPM）和工作流引擎，用于执行和自动化基于 BPMN 模型的业务流程。


### 登录
&emsp;&emsp;本模块使用该模块的用户都完成了登录，后端已经建立了 Session。登录模块由其他小组负责，我们需要的功能是在登录完成后打开与后端交互的 Session，并使用唯一ID对用户进行标识，将 ID 保存在会话中。本模块设计了类似的替代登录界面供调试使用，但没有对用户的身份信息做任何核验，知识单纯地获取了用户 ID。
### BPMN绘制
&emsp;&emsp;BPMN 绘制的页面有许多现成软件可以参考，且绘制 BPMN 图并非本项目重点，本项目提供若干
&emsp;&emsp;
### 部署流程


### 启动流程实例


### 操作任务


### 历史记录查询


## API List
### 实体类
**MyTaskEntity**
|Type| Key|
|:----:|:----:|
|String| Id|
|String| name|
|String| owner|
|String| assignee|
|String| description|
|Date  |  createTime|
|Date  |  dueDate|

**MyDeploymentEntity**
|Type| Key|
|:----:|:----:|
|String |Id|
|String |name|
|String |description|
|Boolean| suspended|

**MyTaskHistoryEntity**
|Type| Key|
|:----:|:----:|
|String| Id|
|Date  | startTime|
|Date  | endTime|
|String| assignee|
|String| activityName |

**MyTaskEntity**
|Type| Key|
|:----:|:----:|

### 部署





| URL      | 功能     | 调用方法     | 传参方式     | 传入参数  | 返回值 |
| ----------- | ----------- | ----------- | ----------- | ----------- | ----------- |
|/deployment /getAll     | 获取所有部署     | get     | /     | /  | MyTaskEntity |
| /deployment/createInstance     | 启动流程  | get     | /     | /  | / |
| 。。。 |

# 分工与日志
## 分工情况
**杨昊**


**何宇轩**

**张远驰**


## 进度日志
### 迭代1


