# CSDQA

CSDQA(Computer Science Diagrams Question Answering) dataset is the first graphic diagrams dataset and the first DQA dataset in Computer Science domain. It contains a total of 1,294 diagrams in 12 categories from five undergraduate courses: *Data structure*, *Principles of Computer Networks*, *Computer Architecture*, *Digital Logic Circuit*, and *Computer Operating System*. 

## Annotation

We put the annotation files of each category independently in a file with the same name as the category. The following uses *binary tree* as the example to illustrate the content of the annnotation:

Each diagram has global labeling information, including: filename, size, file_attributres(source, class, caption). Further information about objects and relations in the diagram, each object has: shape_attrbutes(x, y, width, height)、region_attrbites(id, obj_label, description). Each relation has: shape_attrbutes(x, y, width, height)、region_attrbites(id, logic_label, relationship). 

It should be noted that the relation in the diagram is to mark logical symbols such as arrows and lines. To distinguish between relations and objects, you need to specify in "Type: object" or "Type: logical symbol".

## QA

Three categories diagrams  (*Queue, Stack, Binary-tree*) in CSDQA correspond to four or six questions (including two simple questions), and the diagrams of the remaining categories correspond to two simple questions. Each diagram corresponds to a ".txt" file with the same name. The correct answer is in the last line of the file.

## Version

2. 1(2021-11-22)

## Contact

Shaowei Wang(wang97@stu.xjtu.edu.cn)



License for Non-Commercial Use





 

