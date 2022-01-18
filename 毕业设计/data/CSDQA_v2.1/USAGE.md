## Update log

* 2021/11/22 In version 2.1, we updated the following contents of CSDQA:

1. Fixed the error that the flowchart diagrams do not correspond to the order of the question-answer pairs texts.
2. Fixed the problem that the format of question-answer pairs are not uniform.
3. Corrected some grammatical issues in the question-answer pairs.
4. Added data division of DQA task.
5. Reorganized a new json file format for DQA task.



## Usage

* *data* folder is used to store the raw contents of the dataset, where diagrams, question-answer pairs and annotations are organized according to categories.

* *split_data* is used for DQA task. *all_diagram* and *all_Q* folders respectively store the re-ordered diagrams and the corresponding question- answer pairs. (If you try to correspond the new order to the diagrams divided by categories, the relevant matches are stored in *match.json*.)

* *cords.json* stores the coordinates of the objects contained in each diagram. This content is to be unified with the annotation contained in the traditional VQA task.

* *CSDQA_train_val* divides the diagrams into training set (832) and validation set (357) for DQA task. The diagrams are stored in *D* in the corresponding folder, and the question-answer pairs are stored in *Q*.

* In order to facilitate the standardization of data processing for researchers, we organized a new annotation file: *Q.json*. In this file, each question is the root directory, and an example of its organization is:

  *1:*

  *|--- question: How many contents are in the array?*

  *|---type: MC*

  *|---difficulty: easy*

  *|---diagram_path: ./data/CSDia_train_val/train/D\10.png*

  *|---answer*

  *|		|---d: 13*

  *|		|---c: 10*

  *|		|---b: 5*

  *|		|---a: 9*
  *|---correct_answer: a*

  *|---split: train*

* Among them, the types are MC (multiple choice) and TF (true or false); the difficulty of the question includes easy and complex. Researchers can parse this file to organize the dataloader.

  

  

